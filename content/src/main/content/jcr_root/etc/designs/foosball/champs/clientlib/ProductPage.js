function validatePrice() {
    var price = document.getElementById("productPrice").value;
    if(isNaN(price)) {
        alert("Please enter valid price.");
        document.getElementById("productPrice").value = "";
        return false;
    }
}