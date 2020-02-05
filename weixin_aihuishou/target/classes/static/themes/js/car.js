
$(function() {
    // 数量减
    $(".minus").click(function() {
        var t = $(this).parent().find('.num');
        var prod_id = $(this).parent().find('.prod_id').val();
        t.text(parseInt(t.text()) - 1);
        if (t.text() <= 1) {
            t.text(1);
        }
        TotalPrice(prod_id,'minus');
    });
    // 数量加
    $(".plus").click(function() {
        var prod_id = $(this).parent().find('.prod_id').val();
        var t = $(this).parent().find('.num');
        t.text(parseInt(t.text()) + 1);
        if (t.text() <= 1) {
            t.text(1);
        }
        TotalPrice(prod_id, 'plus');
    });
   
    //计算
    function TotalPrice(prod_id,operation) {
        var allprice = 110;
          $.ajax({        
          	type: 'POST',        
          	url: "/updateCartFromAjax",       
          	cache: false,  //禁用缓存        
          	data:JSON.stringify({"prod_id":prod_id,"operation":operation}),        
          	contentType: "application/json",        
          	dataType: "json",        
          	success: function (result) { 
          	             $("#AllTotal").text(result.total_price);
          	         // allprice = result.total_price;     
          	           }    
          	        });

       
      
        //输出全部总价
    }
});
