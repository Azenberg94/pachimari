
module.exports = function(app, models){
	
	require("./index")(app);
	require("./login")(app, models);
	require("./signup")(app, models);
	require("./adminProduct")(app, models);
}

