$(document).ready(function(){		
	
	var i = document.getElementById('txtAdr').value;
			
	var geocoder = new daum.maps.services.Geocoder();
			
	var callback = function(result, status) {
		if (status === daum.maps.services.Status.OK) {
			//console.log(result);
			 
			$('#latitude').val(result[0].x)
			$('#longtitude').val(result[0].y)
			
			var xx = result[0].x
			var yy = result[0].y
			
			console.log(xx)
			console.log(yy)
			
			var container = document.getElementById('map');
			var options = {
				center: new daum.maps.LatLng(yy, xx),
				level: 3
			};

			var map = new daum.maps.Map(container, options);
			
			var marker = new daum.maps.Marker({
			    position: new daum.maps.LatLng(yy, xx), 
			    map: map 
			});
		}
	};		
	geocoder.addressSearch(i, callback, 1);			
})

