module.exports = function(app, models) {

    var api = models.myApi;
    var rp = require('request-promise');

    app.post('/order', function(req, res) {

        if(!req.session.type){
            res.redirect("/");
        }else{

        }

    });

}

