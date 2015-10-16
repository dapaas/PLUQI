$(document).ready(function(){
	var pagebody = $("#pagebody");
	var themenu  = $("#navmenu");
	var topbar   = $("#toolbarnav");
	var content  = $("#content");
	var viewport = {
    	width  : $(window).width(),
    	height : $(window).height()
	};
	// retrieve variables as 
	// viewport.width / viewport.height
	
	function openMenu() { 
		$(function () {
		    topbar.animate({
		       left: "290px"
		    }, { duration: 300, queue: false });
		    pagebody.animate({
		       left: "290px"
		    }, { duration: 300, queue: false });
		});
	}
	
	function closeMenu() {
		var closeMenu = $(function() {
	    	topbar.animate({
	            left: "0px"
	    	}, { duration: 180, queue: false });
	    	pagebody.animate({
	            left: "0px"
	    	}, { duration: 180, queue: false });
		});
	}

	// checking whether to open or close nav menu
	$("#menu-btn").on("click", function(e){
		e.preventDefault();
		var leftval = pagebody.css('left');
		
		if(leftval == "0px") {
		    openMenu();
		}
		else { 
		    closeMenu();
		}
	});
	
	// loading page content for navigation
	//$("a.navlink").on("click", function(e){
	//	e.preventDefault();
		
		
	//	closeMenu();
		
	//	$(function() {
	//		topbar.css("top", "0px");
	//		window.scrollTo(0, 1);
	//	});
		
		
	//	var linkurl = $(this).attr("href");
	//	if (linkurl.length < 2) return;
	//	var imgloader = '<center style="margin-top: 30px;"><img src="img/preloader.gif" alt="loading..." /></center>';
	//	content.html(imgloader);
	//	var linkhtmlurl = linkurl.substring(1, linkurl.length);
	//	setTimeout(function () { content.load(linkhtmlurl, function () { /* no callback */content.empty(); }) }, 1200);
	//});
});