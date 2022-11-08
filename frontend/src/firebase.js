import { initializeApp } from "firebase/app";
import { getStorage  } from "firebase/storage";

// TODO: Replace the following with your app's Firebase project configuration
// See: https://firebase.google.com/docs/web/learn-more#config-object
const firebaseConfig = {
  apiKey: process.env.VUE_APP_FIREBASE_API_KEY,
  authDomain: process.env.VUE_APP_FIREBASE_AUTH_DOMAIN,
  databaseURL: process.env.VUE_APP_FIREBASE_DATABASE_URL,
  projectId: process.env.VUE_APP_FIREBASE_PROJECT_ID,
  storageBucket: process.env.VUE_APP_FIREBASE_STORAGE_BUCKET,
  messagingSenderId: process.env.VUE_APP_FIREBASE_MESSAGING_SENDER_ID,
  appId: process.env.VUE_APP_FIREBASE_APP_ID,
  measurementId: process.env.VUE_APP_FIREBASE_MEASUREMENT_ID
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);


// Initialize Cloud Storage and get a reference to the service
getStorage(app)
// const storage = getStorage(app);
// const storageRef = ref(storage, 'hi/logo.png');

// 'file' comes from the Blob or File API
// uploadBytes(storageRef, file).then(() => {
//   console.log('Uploaded a blob or file!');
// });


// Get the download URL
// getDownloadURL(storageRef)
//   .then((url) => {
//     // Insert url into an <img> tag to "download"
//     window.open(url)
//   })
//   .catch((error) => {
//     // A full list of error codes is available at
//     // https://firebase.google.com/docs/storage/web/handle-errors
//     switch (error.code) {
//       case 'storage/object-not-found':
//         // File doesn't exist
//         break;
//       case 'storage/unauthorized':
//         // User doesn't have permission to access the object
//         break;
//       case 'storage/canceled':
//         // User canceled the upload
//         break;

//       // ...

//       case 'storage/unknown':
//         // Unknown error occurred, inspect the server response
//         break;
//     }
//   });