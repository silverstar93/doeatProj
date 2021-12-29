<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	    <script src="../js/jquery-3.3.1.min.js"></script>
	    <script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9c8f4b16a0c59154c786a22345c07048&libraries=services,clusterer,drawing"></script>
	<style>
	

    .title {z-index : 2; position: absolute; border:1px solid #000; bottom: 40px; width: 130px;height: 30px; margin-left: -70px; text-align: center; overflow: hidden; padding: 5px 0 0 5px; background: #eee; font-size: 18px;font-weight: bold;}

    </style>
    
    


<div id="map" style="width: 500px; height: 400px;"></div>






<script>



var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
    center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표

};  

//지도를 생성합니다    
var map = new daum.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new daum.maps.services.Geocoder();

//지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
var bounds = new daum.maps.LatLngBounds();    

var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 



 ////////////////
	

var listTitle =  '${tit}'.split(",");
var listData = '${addr}'.split(",");
var listRid = '${rid}'.split(",");

listData.forEach(function(groupAddress, index) {
geocoder.addressSearch(groupAddress, function(result, status) {

// 정상적으로 검색이 완료됐으면 
 if (status === daum.maps.services.Status.OK) {

    var coords = new daum.maps.LatLng(result[0].y, result[0].x);

    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new daum.maps.Size(24, 35); 
    
    // 마커 이미지를 생성합니다    
    var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize); 
    

    // 결과값으로 받은 위치를 마커로 표시합니다
    var marker = new daum.maps.Marker({
        map: map,
        position: coords,
        image : markerImage 
    });

    
    var content = 
    '        <div class="title"><a href="../restaurant/ResDetail?rid=1234" target="_blank" class="link">' + listTitle[index] + 
    '            </a>' + 
    '</div>';

/*     var overlay = new daum.maps.CustomOverlay({
        content: content,
        map: map,
        position: coords
    }); */
    
    var infowindow = new daum.maps.InfoWindow({
	    content : '<a href="../restaurant/ResDetail?rid='+listRid[index]+'" target="_blank" class="link">'+listTitle[index]+'</a>',
	    		
	    removable : true
	});

    daum.maps.event.addListener(marker, 'click', function() {
        // 마커 위에 인포윈도우를 표시합니다
        infowindow.open(map, marker);  
  });
    infowindow.open(map, marker);  
    bounds.extend(coords);
    map.setBounds(bounds);

    
    
 } 
});
});    


    overlay.setMap(map);

	
</script>
