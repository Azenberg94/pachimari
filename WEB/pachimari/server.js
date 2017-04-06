// server.js

// set up ======================================================================
// get all the tools we need
var express  = require('express');
var bodyParser = require('body-parser');
var app      = express();
var port     = process.env.PORT || 8080;
var models = require("./models");
var cookieParser = require('cookie-parser');
var session  = require('express-session');

app.use(session({
	secret: 'vidyapathaisalwaysrunning',
	resave: true,
	saveUninitialized: true
 } )); // session secret
 
 
app.use(bodyParser.urlencoded({
	extended: true
}));
app.use(bodyParser.json());
app.use(cookieParser());


app.set('view engine', 'ejs'); // set up ejs for templating

// routes ======================================================================
require('./app/routes.js')(app, models); // load our routes and pass in our app and fully configured passport

// launch ======================================================================
app.listen(port);
console.log('The magic happens on port ' + port);