module.exports = function(app, models) {

    var api = models.myApi;
    var rp = require('request-promise');

    app.post('/order', function(req, res) {

        if(!req.session.type){
            res.redirect("/");
        }else{

            var user;
            var items = [];
            var amount = 0;

            rp({
                url: "http://"+api.host+"/user/"+req.session.login,
                method: "GET",
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(function(body) {

                user = JSON.parse(body);

                req.session.products.forEach(function(product) {
                    items.push(product);
                    amount += parseInt(product.price);
                });

                console.log(amount);


                rp({
                    url: "http://"+api.host+"/orders/" ,
                    method: "POST",
                    headers:{
                        'Content-Type': 'application/json'
                    },
                    json:{
                        "user": user,
                        "items": items,
                        "amount": parseInt(amount)
                    }
                }).then(function(body) {

                    var response = {
                        status  : 200,
                        success : 'Add Successfully'
                    }

                    res.end(JSON.stringify(response));
                }).catch(function(err) {
                    console.log(err);
                });


            }).catch(function(err) {

            });

            /*
            rp({
                url: "http://"+api.host+"/orders/" ,
                method: "POST",
                headers:{
                    'Content-Type': 'application/json'
                },
                json:{
                    "user": req.body.name,
                    "items": req.body.brand,
                }
            }).then(function(body){
                //console.log("Body 2 : " + body)
            }).catch(function (err) {
                msgErrornull, = "Erreur lors de la création du produit ! Merci de réessayer. !"
                rp("http://"+api.host+"/categories/" ).then(function(body){
                    listCat = JSON.parse(body);
                }).then(setTimeout(function(){rp("http://"+api.host+"/product/" ).then(function(body){
                        res.render('adminProduct.ejs', {msgError:msgError, msgValidation : msgValidation, listProduct :  JSON.parse(body), listCat : listCat, session : req.session});
                    })},500)
                );

            });
            */

        }

    });

    app.get("/orders", function(req, res) {

        rp({
            url: "http://"+api.host+"/orders/users/"+req.session.login
        }).then(function(body) {

            console.log(body);
            res.render('order.ejs', { orders: JSON.parse(body), session: req.session });

        }).catch(function(err) {
            console.log(err);
        });

    });

}

