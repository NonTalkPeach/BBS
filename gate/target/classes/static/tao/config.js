//配置信息
const FILE_SERVER_URL = "http://192.168.0.106:801";
const WEB_URL = 'http://127.0.0.1' +
    ''

//导航栏渲染函数
function navStarter() {
    let token = window.sessionStorage.getItem("token");
    if (token != null && token.length > 20) {
        document.getElementById('isAuth').style.display = 'none';
        let strings = token.split("."); //截取token，获取载体
        let tokenInfo = JSON.parse(decodeURIComponent(escape(window.atob(strings[1].replace(/-/g, "+").replace(/_/g, "/"))))); //解析，需要吧‘_’,'-'进行转换否则会无法解析

        document.getElementById("url01").href = "/my/" + token.substring(1,token.length-1)
        document.getElementById("url02").href = "/editor/" + token.substring(1,token.length-1)
        document.getElementById("url03").href = "/index/" + token.substring(1,token.length-1)

        let temLi01 = document.createElement("li");
        let temImg = document.createElement("img");
        temImg.src = FILE_SERVER_URL + tokenInfo.userInfo.avatarUrl;
        temImg.className = "taoAvatar";
        temImg.id = "userAvatarImgID";
        temLi01.appendChild(temImg)
        document.getElementById("navigation").appendChild(temLi01);

        let temLi02 = document.createElement("li");
        temLi02.innerText = tokenInfo.userInfo.username;
        temLi02.id = "usernameID"
        document.getElementById("navigation").appendChild(temLi02);
    }
}

//时间格式化函数
function timeFmt(value) {
    var date=new Date(value);
    var year=date.getFullYear();
    var month=date.getMonth()+1;
    month=month>9?month:('0'+month);
    var day=date.getDate();
    day=day>9?day:('0'+day);
    var hh=date.getHours();
    hh=hh>9?hh:('0'+hh);
    var mm=date.getMinutes();
    mm=mm>9?mm:('0'+mm);
    var ss=date.getSeconds();
    ss=ss>9?ss:('0'+ss);
    var time=year+'-'+month+'-'+day+' '+hh+':'+mm+':'+ss;
    return time;
}

