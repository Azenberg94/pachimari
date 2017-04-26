$(document).ready(function() {
   $(".addToCart").on("click", function(e) {

       e.preventDefault();

       $.ajax({
           url : '/cart/add',
           type : 'POST',
           data: $(this).parent().serialize(),
           dataType : 'json',
           success : function(response, status) {
               alert(response.success);
           }
       });

   })
});