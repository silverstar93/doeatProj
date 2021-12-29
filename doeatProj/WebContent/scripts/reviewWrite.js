 $(document).ready(function(){ 
	
	$('.taste').each(function(i){		
		$(this).on({
			click:function(){
				$('#taste').val(i+1)
				for(var no=0; no<=i+1; no++){					
					$('#taste'+no).html('★')
				}				
			}			
		})
	})	
	
	$('.mood').each(function(i){
		
		$(this).on({
			click:function(){
				$('#mood').val(i+1)
				for(var no=0; no<=i+1; no++){
					$('#mood'+no).html('★')
				}
			}			
		})
	})
	
	$('.cs').each(function(i){
		
		$(this).on({			
			click:function(){
				$('#cs').val(i+1)
				for(var no=0; no<=i+1; no++){
					$('#cs'+no).html('★')
				}
			}			
		})
	})
}) 	
	function resetStar(){
		
		$('#cs').val(0)
		$('#mood').val(0)
		$('#taste').val(0)
		
		for(var no=0; no<=5; no++){
			$('#cs'+no).html('☆')
			$('#mood'+no).html('☆')
			$('#taste'+no).html('☆')
		}		
	}