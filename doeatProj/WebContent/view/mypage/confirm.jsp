<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>

		if(confirm('${msg}')){
			
			$.ajax('../ajax_jisu/pnoSch?mem='+'${param.mem}', {
				success : function(dd) {
				alert("탈퇴되었습니다.");
				
				if(dd=='1'){
					location.href ="user";
				} else{
					location.href = "partner";
				}
				}
			});
			
		} else{
			location.href = 'uDetail?mem='+'${param.mem}';
		}
	
</script>