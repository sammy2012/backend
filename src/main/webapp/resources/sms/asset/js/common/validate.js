define("common/alertPopover",function(){var t=_.template('<div class="popover fade top in <%=className%>"><div class="arrow"></div><div class="popover-content"><%=msg%></div></div>'),r=function(r,e,n){var o=$({}),a=$(t({msg:e,className:n?"alert":"alert-error"}));$(document.body).append(a);var i=r.offset();return a.css({left:i.left+"px",top:i.top-a.height()+"px"}),o.destroy=function(){a.remove()},a.show(),o};return window.alertPop=r,r}),define("common/validate",function(require){function t(t){return t.val()}function r(t){var r=t.attr("validateName")||"",e=n(t,r+"不能为空");t.one("click",function(){e.destroy()})}function e(t){for(var e=t.attr("validate").split(","),n=null;n=e.pop();)if(o[n]&&!o[n].call(o,t))return r(t),!1;return!0}var n=require("common/alertPopover"),o={notNull:function(r){var e=t(r);return e.trim()}},a=function(t){var r=$(t).find("[validate]"),n=r.length;if(n)for(var o=0;n>o;o++)if(!e(r.eq(o)))return!1;return!0};return window.validateForm=a,a});