$(document).ready(function() {
var table = $('table#users').DataTable({  
        'ajax' : '/data/users',
        'serverSide' : true,
        columns : [ {
            data : 'personId'
        }, {
            data : 'firstname'
        }, {
            data : 'lastname'
        }, {
            data : 'email'
        }, {
            data : 'role.userRole',
            render: function(data, type, row) {
                return data ? data : '';
            }
        }]
    });
}