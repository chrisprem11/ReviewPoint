function addReview() {
	var review = $('#review').val();
	var rating = $('#rating').val();
	var carId = $('#carId').val();
	$('#add').text("Adding..");
	$("#add").prop("disabled", true);

	$.ajax({
		type : "post",
		url : "/review",
		data : 'review=' + review + '&rating=' + rating,+ '&carId=' + carId,
		success : function(response) {
			$('#info').show(200);
			$('#info').html(response);
			$('#review').val('');
			$('#rating').val('');
			$('#carId').val('');
			$('#add').text("Add review");
			$('#add').prop("disabled", false);
			hideMessage();
		},
		error : function(xhr, status, e) {
			alert(xhr.responseText);
		}
	});
	return false;
}

function hideMessage() {
	setTimeout(function() {
		$("#info").hide(500);
	}, 2500);
}