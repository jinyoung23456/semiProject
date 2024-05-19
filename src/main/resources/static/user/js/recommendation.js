function changeImage() {
    var image = document.getElementById('myImage');
    if (image.src.match("/img/icon/good.png")) {
        image.src = "/img/icon/notGood.png";
    } else {
        image.src = "/img/icon/good.png";
    }
};