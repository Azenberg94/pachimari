module.exports = function(app, models) {

    var api = models.myApi;
    var rp = require('request-promise');

    app.get('/product', function(req, res) {
        rp("http://"+api.host+"/product/" ).then(function(body) {
            res.render('product.ejs', {session: req.session, products : JSON.parse(body)}); // load the product.ejs file
        });
    });

}
