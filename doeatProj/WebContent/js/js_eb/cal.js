function calendar(year,month, id){ //달력 함수  
    //내장 객체
    var nowDate = new Date(); //오늘 날짜 객체 선언  
    var nYear = nowDate.getFullYear(); //오늘의 년도  
    var nMonth = nowDate.getMonth(); //오늘의 월 ※ 0월부터 시작  
    var nDate = nowDate.getDate(); //오늘의 날  
    var nNumday = nowDate.getDay(); //오늘의 요일 0=일요일...6=토요일  
    var endDay=new Array(31,28,31,30,31,30,31,31,30,31,30,31); //각달의 마지막 날짜  
    var dayName=new Array("일", "월", "화", "수", "목", "금", "토"); // 숫자 요일을 문자 요일 바꿀 함수  
    var col=0; //나중에 앞뒤 빈 날짜칸 계산   
    if (year==null){ //null 일경우, 처음 페이지의 년도는 현재 년도를 가져옴   
        year=nYear;
    } 

    if (month==null){ //null 일경우, 처음 페이지의 월은 현재 월을 가져옴
        month=nMonth;  
    }

    eDate= new Date(); //달을 넘길 때 바뀌는 날짜
    eDate.setFullYear(year); //바뀌는 년도 
    eDate.setMonth(month); //바뀌는 월 
    eDate.setDate(1); // 날짜는 1일로 설정  
    var fNumday=eDate.getDay(); //넘길때 달 1일 요일 (숫자)  
    var lastDay=endDay[eDate.getMonth()]; //바뀌는 월의 마지막 날짜  

    if ((eDate.getMonth()==1)&&(((eDate.getYear()%4==0)&&(eDate.getYear() %100 !=0))||eDate.getYear() % 400 ==0 )){
        //0월 부터 시작하므로 배열의 첫번째는 2월-> 윤달 계산 4년마다 29일, 100년는 28일, 400년 째는 29일
        lastDay=29;
    }   

    calendarStr  = "<table>"  
    calendarStr += "<tr align=center><td valign=middle>"  
    calendarStr += "<a href=javascript:calendar("+year+","+(month-1)+") class=preNext><</a>" //월을 넘길때 빼기 -1을 해서 넘긴다(년도는 자동 계산)  
    calendarStr += "</td><td colspan=5 >"  
    calendarStr += "<font size=3 color=black><b>"+eDate.getFullYear()+"년 "+(eDate.getMonth()+1)+"월</b></font> "//해당하는 년도와 월 표시  
    calendarStr += "</td><td valign=middle>"  
    calendarStr += "<a href=javascript:calendar("+year+","+(month+1)+") class=preNext>></a>" //월을 넘길때 더하기 +1을 해서 넘긴다(년도는 자동 계산)  
    calendarStr += "</td></tr><tr>"  
    for (i=0;i<dayName.length;i++){            
        calendarStr += "<td class=week>"+dayName[i] + "</td>" //숫자 요일을 날짜로 입력  
    }  

    calendarStr += "</tr><tr align=center>"  

    for (i=0;i<fNumday;i++){ // 첫번째 날짜의 숫자 요일을 구해서 그전까지는 빈칸 처리  
        calendarStr += "<td>&nbsp;</td>"   
        col++;                       
    }  

    for ( i=1; i<=lastDay; i++){ //해당 월의 달력 출력  
        if(eDate.getFullYear()==nYear&&eDate.getMonth()==nMonth&&i==nDate){ //오늘이면 today 스타일로 표시  
            calendarStr += "<td class=today>"+i+"</td>"   
        }
        else if(eDate.getFullYear()<nYear ||  (eDate.getFullYear()==nYear && eDate.getMonth()<nMonth)
              || (eDate.getFullYear()==nYear && eDate.getMonth()==nMonth && i<(nDate+0))){     //nDate+15 몇일까지 막을것인지
           if(col==0){//일요일 
                calendarStr += "<td class=sunday>"+i+"</td>"  
            }
            else if(1<=col&&col<=5){//평일  
                calendarStr += "<td class=workday>"+i+"</td>"   
            }
            else if(col==6){ //토요일  
                calendarStr += "<td class=satday>"+i+"</td>"   
            }
        }else{  
            if(col==0){//일요일 
                calendarStr += "<td class=sunday onClick=datePicker("+year+","+month+","+i+",'"+id+"')>"+i+"</td>"  
            }
            else if(1<=col&&col<=5){//평일  
                calendarStr += "<td class=workday onClick=datePicker("+year+","+month+","+i+",'"+id+"')>"+i+"</td>"   
            }
            else if(col==6){ //토요일  
                calendarStr += "<td class=satday onClick=datePicker("+year+","+month+","+i+",'"+id+"')>"+i+"</td>"   
            }  
        }             
        col++;  

        if(col==7){ //7칸을 만들면 줄 바꾸어 새 줄을 만들고 다시 첫 칸부터 시작  
            calendarStr += "</tr><tr align=center>"  
            col=0;  
        }  
    }     

    for (i=col;i<dayName.length;i++){ //마지막 날에서 남은 요일의 빈 칸 만들기  
        calendarStr += "<td>&nbsp;</td>"  
    } 

    $('#ddateCal1').html(calendarStr);  
   
    $('#ddateCal1').css({
       'visibility': 'visible'
    })
    
}



function datePicker(yy,mm,dd,id){
 
 console.log(yy+"년 "+(mm+1)+"월"+dd+"일"+id);
 
 if((mm+1)<10){
    mm="0"+(mm+1);
 }else{
    mm=mm+1;
 }
 if((dd)<10){
    dd="0"+(dd);
 }
 

 $('#ddate').val(yy+"-"+mm+"-"+dd);
 
 
}



