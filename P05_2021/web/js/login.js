/*
* All JS Code
*/

let elemnt = {
    btn_sign_in: document.querySelector('.sign-in-btn-tab'),
    btn_sign_up: document.querySelector('.sign-up-btn-tab'),
    sign_in_tab: document.querySelector('.sign-in-tab'),
    sign_up_tab: document.querySelector('.sign-up-tab'),
    global: document.querySelector('html'),
    body_: document.querySelector('body'),
    form_group: document.querySelector('.form-group'),
    toggle_switch: document.querySelector('.toggle-switch')
};

let tmp_value = {
    color_btn_1: '',
    color_btn_2: ''
}

let color_collection = {
    dark_mode: {
        dark_mode_principal: '#222222',
        dark_mode_secondary: '#535353',
        dark_mode_tertiary: '#7D7C7C'
    },
    purple_mode: {
        purple_mode_principal: '#7519EB',
        purple_mode_secondary: '#A259FF',
        purple_mode_tertiary: '#B982FF'
    } 
}
let flag = false;

elemnt.btn_sign_in.addEventListener('click', ()=>{
    elemnt.sign_up_tab.style.display = 'none';
    elemnt.sign_in_tab.style.display = 'flex';
    flag = false;
    render(elemnt.btn_sign_up, elemnt.btn_sign_in);
});

elemnt.btn_sign_up.addEventListener('click', ()=>{
    elemnt.sign_in_tab.style.display = 'none';
    elemnt.sign_up_tab.style.display = 'flex';
    flag = true;
    render(elemnt.btn_sign_in, elemnt.btn_sign_up);
});

elemnt.toggle_switch.addEventListener('change', ({target})=>{
    if(!target.checked){
        elemnt.global.style.setProperty('--purple-principal', color_collection.purple_mode.purple_mode_principal);
        elemnt.global.style.setProperty('--purple-secondary', color_collection.purple_mode.purple_mode_secondary);
        elemnt.global.style.setProperty('--purple-tertiary', color_collection.purple_mode.purple_mode_tertiary);
        _render(elemnt.btn_sign_up, elemnt.btn_sign_in);
    }else{
        elemnt.global.style.setProperty('--purple-principal', color_collection.dark_mode.dark_mode_principal);
        elemnt.global.style.setProperty('--purple-secondary', color_collection.dark_mode.dark_mode_secondary);
        elemnt.global.style.setProperty('--purple-tertiary', color_collection.dark_mode.dark_mode_tertiary);
        _render(elemnt.btn_sign_up, elemnt.btn_sign_in);
    }
});


function render(element1, element2){
    let value = getComputedStyle(elemnt.global);
    element1.style.color = `${value.getPropertyValue('--purple-tertiary')}`;
    element2.style.color = `${value.getPropertyValue('--purple-white-text')}`; 
}

function _render(element1, element2){
    let value = getComputedStyle(elemnt.global);
    if(flag){
        element2.style.color = `${value.getPropertyValue('--purple-tertiary')}`;
        element1.style.color = `${value.getPropertyValue('--purple-white-text')}`;     
    }else{
        element1.style.color = `${value.getPropertyValue('--purple-tertiary')}`;
        element2.style.color = `${value.getPropertyValue('--purple-white-text')}`;    
    }
}

