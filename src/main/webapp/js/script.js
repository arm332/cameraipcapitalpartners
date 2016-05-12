if(top.location != self.location) {
    top.location = self.location;
}

$(document).ready(function() {

    // Activate current navbar item
    $path = location.pathname.split('/');
    $('.nav a[href="/'+$path[1]+'"]').parents('li').addClass('active');

    // Generic confirmation dialog
    $('#confirm-dialog').on('show.bs.modal', function(e) {
        $message = $(e.relatedTarget).attr('data-message');
        $(this).find('.modal-body p').text($message);
        $href = $(e.relatedTarget).data('href');
        $(this).find('.btn-primary').attr('href', $href);
    });

    // Auto camera reloading

    $timer = {};

    $trigger = function(img) {
        $id = $(img).attr('id');
        clearTimeout($timer[$id]);

        $src = null;
        $url = $(img).data('url');
        $interval = $(img).data('interval');
        $cache = parseInt($interval);
        if(isNaN($cache)) $cache = 10;

        if ($cache > 0) {
            // Image
            $rnd = new Date().getTime();
            $src = $url.replace(/video.cgi/i, 'image.jpg')
                .replace(/(cache=)[^\&]+/, '$1' + $cache)
                .replace(/(rnd=)[^\&]+/, '$1' + $rnd);

            $timer[$id] = setTimeout($trigger, ($cache * 1000), img);
        }
        else {
            // Video
            $src = $url.replace(/image.jpg/i, 'video.cgi');
        }

        $(img).attr('src', $src);
        //console.log('id: ' + $id + ', src: ' + $src + ', cache: ' + $cache);
    }

    // Start auto camera loading

    $('.cam img').one('load', function() {
        $trigger(this);
    });

    // On camera loading error

    $('.cam img').error(function() {
        $id = $(this).attr('id');
        clearTimeout($timer[$id]);
        $src = $(this).attr('src');
        console.log('Error, id: ' + $id + ', src: ' + $src);
        $(this).attr('src', 'image320x240.png');
    });

    // Change camera loading interval

    $('.cam ul li a').on('click', function() {
        $button = $(this).parents('.cam').find('.dropdown-toggle');
        $button.html($(this).text() + ' <span class="caret"></span>');
        $img = $(this).parents('.cam').find('img');
        $img.data('interval', $(this).text());
        //console.log($img.data('interval'));
        $trigger($img);
    });
    
    // Change all cameras loading interval

    $('.pull-right ul li a').on('click', function() {
        $('.cam ul li a:contains(' + $(this).text() + ')').each(function() {
        	$(this).click();
        });
    });

    // On show camera dialog event

    $('#cam-dialog').on('show.bs.modal', function(e) {
        $header = $(e.relatedTarget).parents('.cam').find('h4');
        $(this).find('.modal-title').text($header.text());
        $camimg = $(e.relatedTarget).parents('.cam').find('img');
    	$dlgimg = $(this).find('.modal-body').find('img');
    	$dlgimg.data('interval', $camimg.data('interval'))
        $dlgimg.data('url', $camimg.data('url'));
    	$dlgimg.attr('src', $camimg.data('url'));
        $trigger($dlgimg);
    });

    // On hide camera dialog event

    $('#cam-dialog').on('hide.bs.modal', function(e) {
    	$dlgimg = $(this).find('.modal-body').find('img');
    	$dlgimg.data('interval', '')
        $dlgimg.data('url', '');
    	$dlgimg.attr('src', '');
        $id = $dlgimg.attr('id');
        clearTimeout($timer[$id]);
    });

    // Show camera tooltip

    $('.cam [data-toggle="popover"]').popover({
        trigger: "focus",
        placement: "bottom",
        container: 'body',
        html: true,
        content: function() {
            $clone = $(this).parents('.cam').find('.collapse').clone();
            $clone.removeClass('collapse');
            return $clone;
        }
    });

    // Drag'n'drop
    
    $dragged = null;
    $dropped = null;
    
    $('.cam').attr('draggable', 'true');

    $('[draggable=true]').on('drag', function(e) {
    	//console.log(e);
    });
    
    $('[draggable=true]').on('dragstart', function(e) {
    	// store a ref. on the draggable elem
        $dragged = $(e.currentTarget);
        $dropped = $dragged;
    });
    
    $('[draggable=true]').on('dragenter', function(e) {
    	//console.log(e);
    });
    
    $('[draggable=true]').on('dragover', function(e) {
    	// prevent default to allow drop
    	e.preventDefault();
    	
    	if (!$dropped.is(e.currentTarget)) {
    		$dropped.children().css('pointer-events', '');
    		$dropped.css('background-color', '');
    		$dropped = $(e.currentTarget);
    		$dropped.css('background-color', '#eee');
    		$dropped.children().css('pointer-events', 'none');
    	}
    });
    
    $('[draggable=true]').on('dragleave', function(e) {
    	//console.log(e);
    });
    
    $('[draggable=true]').on('dragend', function(e) {
    	$dragged = null;
    });
    
    $('[draggable=true]').on('drop', function(e) {
    	// prevent default action (open as link for some elements)
        e.preventDefault();
        
        // move dragged elem to the selected drop target
    	var dropIndex = $dropped.index();
    	var dragIndex = $dragged.index();
    	
    	if (dropIndex > dragIndex) {
    		$dropped.after($dragged);
    	}
    	else if (dropIndex < dragIndex) {
    		$dropped.before($dragged);
    	}
    	else {
    		// dragging to itself
    	}
    	
    	//
		$dropped.children().css('pointer-events', '');
		$dropped.css('background-color', '#fff');
    	$dropped = null;
    	
    	console.log("drop")
    	
    	
    	// Save cumstom order cookie
        var order = [];
        $('.cam > img').each(function(img) {
        	console.log(this.id + ", " + this.alt);
            // Remove all but numbers from ID
        	order.push(this.id.replace(/\D/g, ""));
        });
        //console.log(order);
        //console.log(document.cookie);
        document.cookie = "order=" + order.join("|")
            + "; expires=Fri, 31 Dec 9999 23:59:59 GMT";
        //console.log(document.cookie);
    });

    // Get viewers
    
    $viewer = function() {
    	$.ajax({
    		async: true,
        	cache: false,
        	dataType: 'json',
        	url: '/viewer' 
        })
        .done(function(data) {
        	//console.log(data)
        	$html = '<b>Usuários ativos</b>';
        	$.each(data, function(key, val) {
        		$html += '<br/>' + key;
        	});
        	//$html += '<br/>' + new Date();
    		$('#viewer').html($html);
        	setTimeout($viewer, 1000 * 60 * 1); // 1 min
    	})
    	.fail(function() {
    		console.log('ERROR: at viewers');
    	});
    };
    
    $viewer();
});
