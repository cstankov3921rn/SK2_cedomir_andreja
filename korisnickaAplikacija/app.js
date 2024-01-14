const express = require('express');
const path = require('path');
const app = express();

const BP = require('body-parser');
const Joi = require('joi');
const fs=require('fs');
app.use((req, res, next) => {
  res.setHeader('Referrer-Policy', 'strict-origin-when-cross-origin');
  next();
});


app.use( express.static( path.join(__dirname, 'static') ) );
app.use('/novo-jelo', BP.urlencoded({extended: false}));

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'static', 'index.html'));
});

app.post("/novo-jelo", (req, res) => {
    const shema = Joi.object().keys({
        naziv: Joi.string().trim().min(5).max(25).required(),
        opis: Joi.string().trim().min(1).required(),
        kategorija: Joi.number().min(0),
        cena: Joi.number().greater(0).required()
    });
    const {error, succ} = shema.validate(req.body);
    if(error){
        res.send("Greska: " + error.details[0].message);
	  return;
    }
    req.body.opis.replace(/\r?\n|\r/g, '<br>');
    fs.appendFile("jela.txt",
                "\n" + JSON.stringify(req.body) ,
                 function(err, succ){
                     res.send("Poruka je poslana, očekujte odgovor uskoro");
                 }
            );


})

app.get("/jela", (req, res) => {
    const jela = [];
    fs.readFile('jela.txt', 'utf8', (err, data) => {
        if (err) {
          console.error('Error reading file:', err);
          res.status(500).send({ error: "Greška" });
          return;
        }
        //else…
        const redovi = data.split('\n');
        for(let i=0; i<redovi.length; i++){
            if(redovi[i]=="")
                continue;
            let obj = JSON.parse(redovi[i]);
            jela.push(obj);
        }
        res.json(jela);
    });

})


app.listen(8000);