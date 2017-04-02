
module.exports = function(app, models){
	
	require("./index")(app);
	require("./login")(app, models);
	
}

