
 const express = require("express");
 const bodyParser = require('body-parser');
 const path = require("path");
const { json } = require("express/lib/response");



 const app = express();
 const port = process.env.PORT || "8000";

 app.use(express.json());


 app.post("/create", (req, res) => {
     console.log("Word Created: ", req.body)
    res.status(200).json({
        message: "Logged Word Creation successfully"
    });
  });

  app.post("/retrieve", (req, res) => {
    console.log("Words Retrieved: ", req.body)
   res.status(200).json({
       message: "Logged Word Retrieval successfully"
   });
 });



 app.listen(port, () => {
    console.log(`Listening to requests on http://localhost:${port}`);
  });