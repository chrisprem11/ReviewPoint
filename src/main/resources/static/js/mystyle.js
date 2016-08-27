$(document).ready(function(){
	
	$("#animate").animate({
     	top:'120px',
        left: '25px',
        height: '+=10px',
        width: '+=12px',
     },"slow");
	
    $("#button1").click(function(){
        $("p:first").toggle(1000);
        $(".footer").toggle("slow");
    });
    
    $("#p1").on({
    	click : function(){
    		$(this).css("background-color","blue");
    	}
    });
    
    $("#mouse").hover(function(){
    	alert("Ab tere bin Jee lenge hum");
    });
    
    $("input").focus(function(){
    	$(this).css("background-color","beige");
    });
    
    $("input").blur(function(){
    	$(this).css("background-color","moccasin");
    });
    
    $("#button1").click(function(){
    	$("#l").fadeTo("4000","0.4");
    	
    })
    
    $("#button2").click(function(){
    	$("#content").slideToggle("slow");
    })
    
   
 });

