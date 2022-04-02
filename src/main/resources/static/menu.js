//Mobile menu

let menuButton = document.querySelector("header span");
let closeMenuButton = document.querySelector("header nav span");
menuButton.addEventListener("click", () => {
  let nav = document.querySelector("nav");
  if(nav.classList.contains("open")){
    nav.classList.remove("open");
  }else{
    nav.classList.add("open");
  }
});
closeMenuButton.addEventListener("click", () => {
  let nav = document.querySelector("nav");
  if(nav.classList.contains("open")){
    nav.classList.remove("open");
  }else{
    nav.classList.add("open");
  }
});