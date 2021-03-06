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
        if(isNaN($cache)) $cache = 0;

        if ($cache > 0) {
            // Image
            $rnd = new Date().getTime();
            $src = $url.replace(/video.cgi/i, 'image.jpg')
                .replace(/(cache=)[^\&]+/, '$1' + $cache)
                .replace(/(rnd=)[^\&]+/, '$1' + $rnd);

            $timer[$id] = setTimeout($trigger, ($cache * 1000), img);
            $(img).attr('src', $src);
        }

        //console.log('Load, id: ' + $id + ', src: ' + $src + ', cache: ' + $cache);
    }

    // Start auto camera loading

//    $('.cam img').one('load', function() {
//    		$trigger(this);
//    });

    // On camera partial loading (image corrupt or truncated) 
    
    $('.cam img').on('load', function() {
    	if (this.naturalWidth === 0) {
    		console.log('Image corrupt or truncated: ' + this.src);
    	}
    });

    // On camera loading error

    $('.cam img').on('error', function() {
        console.log('Image error: ' + this.src);
        //$(this).attr('src', 'placeholder.png');
    });

    // Change camera loading interval

    $('.cam ul li a').on('click', function() {
		$button = $(this).parents('.cam').find('.dropdown-toggle');
		$button.html($(this).text() + ' <span class="caret"></span>');
		$img = $(this).parents('.cam').find('img');
		$img.data('interval', $(this).text());
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
		$dlgimg.data('interval', $camimg.data('interval'));
		$dlgimg.data('url', $camimg.data('url'));
		$dlgimg.attr('src', $camimg.data('url'));
		$dlgimg.attr('alt', $header.text());
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
        trigger: "click",
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

    // Get current on-line viewers
    
    $viewers = function() {
	    	$.ajax({
	        	dataType: 'json',
	        	url: '/viewers' 
	    })
        .done(function(data) {
	        	//console.log(data)
	        	$html = '<b>Usuários Ativos</b>';
	        	$.each(data, function(key, val) {
	        		$html += '<br/>' + key;
	        	});
	        	//$html += '<br/>' + new Date();
	    		$('#viewers').html($html);
	    		// Cameras has it's own refresh every 1 min
	    		if ($path[1] != 'camera') {
	    			setTimeout($viewers, 1000 * 60 * 1); // 1 min
	    		}
	    	})
	    	.fail(function() {
	    		console.log('ERROR: at viewers');
	    	});
	};
    
    // Avoid request when not logged in as we won't receive any data anyway
    
    //if ($path[1] != 'login' && $path[1] != 'logout') {
    //    $viewers();    	
    //}
});
