const functions = require("firebase-functions");

const admin = require("firebase-admin");
admin.initializeApp();

exports.sendListenerPushNotification = functions.database.ref("Orders").onWrite((data, context) => {
  console.log(data);
  console.log("<-------->");
  console.log(context.params);
});


const FCMToken = admin.database().ref(`/Users/${getUserWhoPlacedOrder}/FCMToken/`).once("value");

const payload = {
  token: FCMToken,
  notification: {
    title: "cloud function demo",
    body: "message",
  },
  data: {
    body: "message",
  },
};

admin.messaging().send(payload).then((response) => {
  // Response is a message ID string.
  console.log("Successfully sent message: ", response);
  return {success: true};
}).catch((error) => {
  return {error: error.code};
});


