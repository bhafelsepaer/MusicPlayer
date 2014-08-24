$(document).ready(function() {

	$('.error').each(function() {
		if (!($.trim($(this).html()) == '')) {
			$(this).parent('.wrapper').css(({
				'visibility' : 'visible'
			}));
		}
	});

	$('.wrapper').hover(function() {

		$(this).children('.error').stop().show();

	}, function() {
		$(this).children('.error').stop().hide();
	});
});