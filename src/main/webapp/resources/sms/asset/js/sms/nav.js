define("sms/nav",function(){function e(e){if(e)for(var a=e.split(","),n=0;n<a.length;n++){var t=$("."+a[n]);if(!t.length){var r=$("#userMenu li a").eq(0).attr("href");location.href=r}$("."+a[n]).addClass("active")}}return{init:function(a,n){var t={page:"pageIndex"};return $.extend(t,a),n&&"accountant"==n.role.toLowerCase()&&"importPage"!=t.page?void(location.href="./import.html?v="+Math.floor(100*Math.random())):(e(t.page),$.listener.trigger("navReady"),void(window.navReady=1))}}});