var p = {
    page: 1, // 1 Home 2 Rank of Cities 3 Compare Cities
    pagebody:null,
    themenu: null,
    topbar: null,
    content: null,
    viewport: null,
    menuItemId:'home',
    init: function () {
        p.pagebody = $("#pagebody");
        p.themenu = $("#navmenu");
        p.topbar = $("#toolbarnav");
        p.content = $("#content");
        p.viewport = {
            width: $(window).width(),
            height: $(window).height()
        };
        p.loadContent();
        jQuery(".navlink").click(function (e) {
            e.preventDefault();
            p.closeMenu();
            p.content.empty();
            p.content.append(jQuery('<center style="margin-top: 30px;"><img src="img/preloader.gif" alt="loading..." /></center>'));
            p.menuItemId = jQuery(this).attr("id");
            p.loadContent();
        });
        jQuery("#menu-btn").on("click", function (e) {
            e.preventDefault();
            var leftval = p.pagebody.css('left');

            if (leftval == "0px") {
                p.openMenu();
            }
            else {
                p.closeMenu();
            }
        });
    },
    loadContent: function () {
        console.log("loading url " + p.menuItemId);
        p.content.load(p.menuItemId + ".html", function (response, status, xhr) {
            if (status == "error") {
                p.content.empty();
            }
        });
    },
    openMenu: function () {
        $(function () {
            p.topbar.animate({
                left: "190px"
            }, { duration: 300, queue: false });
            p.pagebody.animate({
                left: "190px"
            }, { duration: 300, queue: false });
        });
    },
    closeMenu: function () {
        var closeMenu = $(function () {
            p.topbar.animate({
                left: "0px"
            }, { duration: 180, queue: false });
            p.pagebody.animate({
                left: "0px"
            }, { duration: 180, queue: false });
        });
    }
};