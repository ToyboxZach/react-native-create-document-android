import { NativeModules } from "react-native";

const CreateDocumentAndroid = {
  createDocument(name, type, callback) {
    NativeModules.CreateDocumentModule.createDocument(name, type || "*/*", callback);
  }
};

module.exports = CreateDocumentAndroid;
