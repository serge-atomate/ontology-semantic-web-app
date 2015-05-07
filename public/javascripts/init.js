
function getIndividuals(element,value) {

    $('a.currentCls').removeClass('currentCls');
    element.parent( "li" ).addClass('selected');
    element.addClass("currentCls").addClass("selectedItem");

    $('.unavailable').show();
    $('#loaderInds').show();
    $(".individuals").html('');

    $.ajax({
        type: "GET",
        url: "/individuals",
        data: { q: value }
    }).done(function( results ) {

        var obj = $.parseJSON(results);

//        console.log(obj.individuals);

        var items = [];
        items.push("<div class='page0'>");
        var p = 0;
//        items.push("<ul class='list-unstyled'>");
        $.each( obj, function( key, val ) {

            if(val.length > 0 && $.inArray( "owl:Nothing", val )==-1) {
                $.each( val, function( key2, val2 ) {
                    if(val2.name=="") return;
                    p++;
//            console.log($.type(val2));
//            console.log(val2);
                    items.push( "<li id='indiv-" + key2 + "'><a href='/individ?q=" + val2.id + "'>" + val2.name + "</a></li>" );
                    // if(key2>=20) {
                    //     return false;
                    // }
                    if((key2+1)%20==0) {
                        items.push("</div><div style='display:none;' class='page"+(key2+1)/20+"'>");
                    }
                });

                $(".individuals").append("<h5>Total records found: "+p+"</h5>");

            } else {
                items.push("<li class='noData'>No data found</li>");
                element.addClass("noDataFound");
            }

        });
        items.push("</div>");

        // add pagination html if more than 20 results
        if(p>20) {
            pagination = "<nav class=''><ul class=\"pagination\">";
            for (i = 0; i <= (p/20); i++) {
                pagination += "<li class='lst";
                if(i==0) {
                    pagination += " active";
                }
                pagination += "'><a href=\"page"+i+"\">"+(i+1)+"</a></li>";
            }
            
            if(p>190) {
                pagination += "  <li class='lst'><a>...</a></li>";
            }
            pagination += "  </ul></nav>";


            items.push(pagination);
        }

//        items.push("</ul>");

        $( "<ul/>", {
            // "class": "list-unstyled",
            html: items.join( "" )
        }).appendTo( ".individuals" );

        $('.unavailable').hide();
        $('#loaderInds').hide();

        paginationWork();

       // $(".list-unstyled").html(results);

    }).fail(function( jqXHR, textStatus ) {
        alert( "Request failed: " + textStatus );
        $('.unavailable').hide();
        $('#loaderInds').hide();
    });
}


function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function paginationWork() {
    $(".pagination a").click(function(e) {
        e.preventDefault();

        $(this).parent( "li" ).parent( "ul" ).find('li').removeClass("active");
        $(this).parent( "li" ).addClass('active');

        var href = $(this).attr('href');
        $(this).parent( "li" ).parent( "ul" ).parent("nav").parent("ul").find("div").hide();
        $(this).parent( "li" ).parent( "ul" ).parent("nav").parent("ul").find("div."+href).show();

        // var classAccess = $(this).parent( "li" ).parent( "ul" ).parent("nav").attr("class");
        // var url = document.URL;

        // if(url.indexOf('#') != -1) {
        //     window.location.href = url.replace(url.substr(url.lastIndexOf('#') + 1), ""+classAccess);
        // } else {
        //     window.location.href = url+"#"+classAccess;
        // }
    });
}

$(document).ready(function() {

    // get url and add active class to correspondent item in main menu
    var href = location.pathname;

    $(".mainmenu").find("a").each(function( index ) {
        if(href == $( this ).attr('href')) {
            $(this).addClass('active');
        }
    });

});