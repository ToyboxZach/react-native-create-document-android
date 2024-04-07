import { NativeModules } from "react-native";

const CreateDocumentAndroid = {
  createDocument(name, callback) {
    NativeModules.CreateDocumentModule.createDocument(name, callback);
  },
};

module.exports = CreateDocumentAndroid;
