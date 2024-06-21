// Essentially going to be used for the animations
    const menu = document.querySelector('#mobile-menu')  // Targets the mobile menu
    const menuLinks = document.querySelector('.navbar__menu')  // targets the navbar menu

    // Basically calls two functions in the CSS when the three lines are clicked
    menu.addEventListener('click', function() {
        menu.classList.toggle('is-active'); 
        menuLinks.classList.toggle('active');
    });