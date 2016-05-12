$('#datable').ready(function() {
	var table = $('table#userDetails').DataTable({
		'ajax' : '/data/userList',
		'serverSide' : true,
		columns : [ {
			data : 'personId'
		}, {
			data : 'firstName',
			
		}, {
			data : 'lastname'
		}, {
			data : 'email'
		}, {
			data : 'role.userRole',
			
		}]
	});
});