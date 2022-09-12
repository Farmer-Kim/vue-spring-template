/**
 * byte 구하기
 * param : 입력 값
 * return : byte 수
 */
export function getByte(value) {
    let byteSize = 0;

    if(typeof value === 'undefined' || value === null || value === ''){
        byteSize = 0;
    } else {
        byteSize = (function(s,b,i,c){
            for(b=i=0;c=s.charCodeAt(i++);b+=c>>11?3:c>>7?2:1);
            return b;
        })(value);
    }

    return byteSize;
}

/**
 * 숫자 입력 체크(정수형)
 * param : 입력 값
 * return : true/false
 */
 export function checkOnlyNumber(value) {
     let result = true;
     let reg = /[^0-9]/g;

     if(reg.test(value)){
        alert('숫자만 입력 가능합니다.');
        result = false;
     }

     return result;
 }

 /**
 * 숫자만 추출(정수형)
 * param : 입력 값
 * return : 숫자 값만 추출하여 리턴
 */
  export function getOnlyNumber(value) {
    let result = "";
    let reg = /[^0-9]/g;

    if(reg.test(value)){
        result = value.replace(reg,"");
    }

    return result;
}

/**
 * 숫자 입력 체크(소수점 허용)
 * param : 입력 값, 소수점 길이
 * return : true/false
 */
 export function checkPrimeNumber(value, len) {
    let result = true;
    let reg = /[^0-9\.]/g;

    if(reg.test(value)){
       alert('숫자, 소수점(.)만 입력 가능합니다.');
       result = false;
    }

    if(typeof len === 'undefined' || len === null || len === ''){
        len = 0;
    }

    if(len > 0){
        let prev = 0;
        let next = 0;
        if( value.indexOf('.') != -1 ){
            prev = value.substring(0, value.indexOf('.') );
            next = value.substring(value.indexOf('.')+1 );

            if(len != 0 && next.length > len){
                alert("소수점 "+len+" 자리만 입력할 수 있습니다.");
                result = false;
            }
        }
    }

    return result;
}

 /**
 * 숫자만 추출(소수점 허용)
 * param : 입력 값, 소수점 길이
 * return : 숫자 값(소수점 포함)만 추출하여 리턴
 */
  export function getPrimeNumber(value, len) {
    let result = "";

    if(typeof len === 'undefined' || len === null || len === ''){
        len = 0;
    }

    let reg=(len > 0)? /[^0-9\.]/gi : /[^0-9]/gi;

    if(reg.test(value)){
        result = value.replace(reg,"");
    }

    if(len > 0){
        let prev = 0;
        let next = 0;
        if( value.indexOf('.') != -1 ){
            prev = value.substring(0, value.indexOf('.') );
            next = value.substring(value.indexOf('.')+1 );
            if(len != 0 && next.length > len){
                next = next.substring(0, len);
            }
            result   = prev + '.' + next.replace(/[^\d]/g,'');
        }
    }

    return result;
}
