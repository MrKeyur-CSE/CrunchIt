const functions = require("firebase-functions");

const admin = require("firebase-admin");
admin.initializeApp();


exports.sendNotificationOnOrderUpdate = functions.database.ref('/Orders').onUpdate((snapshot, context) => {
      const original = snapshot.before.val();
      const changed = snapshot.after.val();
      const payload = {
            token: "f7P3QnO6Tead0wJfvNGzuW:APA91bG5xKEcEHUsBvNLZOYNTt0eLKANg8hFRu5LIqF_7L5TwpXLKXQ6mbg8rhnQNIErQbTtgT20uVd8potVd8mZdayOeQZGNFjVLD2oaP9xw91Tu1UwOLad-TxAlE0-xA9WBc53eGM5",
          notification: {
              title: 'cloud function demo',
              body: "Hello people"
          },
          data: {
              body: "This is the data body",
          }
      };
      
      admin.messaging().send(payload).then((response) => {
          // Response is a message ID string.
          console.log('Successfully sent message:', response);
          return {success: true};
      }).catch((error) => {
          return {error: error.code};
      });
})

