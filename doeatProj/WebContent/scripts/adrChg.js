$(document).ready(function(){		
	
	var i = document.getElementById('txtAdr').value;
			
	var geocoder = new daum.maps.services.Geocoder();
			
	var callback = function(result, status) {
		if (status === daum.maps.services.Status.OK) {
			console.log(result);
			$('#latitude').val(result[0].x)
			$('#longtitude').val(result[0].y)
			
			var xx = result[0].x
			var yy = result[0].y
			
		}
	};		

	geocoder.addressSearch(i, callback, 1);		
	
})
