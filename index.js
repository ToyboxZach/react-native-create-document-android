import { NativeModules } from "react-native";

const CreateDocumentAndroid = {
  createDocument(name, type, callback) {
    NativeModules.CreateDocumentModule.createDocument(name, type || "*/*", callback);
  },

  pickDirectory(callback) {
    NativeModules.CreateDocumentModule.pickDirectory(callback);
  }
};

module.exports = CreateDocumentAndroid;
