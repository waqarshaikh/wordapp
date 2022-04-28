
 const express = require("express");
 const bodyParser = require('body-parser');
 const path = require("path");



 const app = express();
 const port = process.env.PORT || "8000";

 app.use(express.json());


 app.post("/", (req, res) => {
     console.log("Note: " + req.body.word)
    res.status(200).send("Logged Note succefully");
  });



 app.listen(port, () => {
    console.log(`Listening to requests on http://localhost:${port}`);
  });