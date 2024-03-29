const url = '/api/admin'
const urlHead = '/api/header'
const header = document.getElementById('head')
const usersTab = document.querySelector('#navTab a:first-child')
const alluserstab = bootstrap.Tab.getOrCreateInstance(usersTab)
const tBody = document.querySelector('tbody')
const delModal = document.getElementById('deleteModal')
const newDelModal = bootstrap.Modal.getOrCreateInstance(delModal)
const editModal = document.getElementById('editModal')
const newEditModal = bootstrap.Modal.getOrCreateInstance(editModal)
const newUser = document.getElementById('newuser')

const firstName = document.getElementById('firstName')
const lastName = document.getElementById('lastName')
const age = document.getElementById('age')
const email = document.getElementById('email')
const password = document.getElementById('password')
const roles = document.getElementById('roles')

const delId = document.getElementById('deleteId')
const delFirstName = document.getElementById('deleteFirstName')
const delLastName = document.getElementById('deleteSurName')
const delAge = document.getElementById('deleteAge')
const delEmail = document.getElementById('deleteEmail')
const delRoles = document.getElementById('deleteRoles')

const editId = document.getElementById('editId')
const editFirstName = document.getElementById('editFirstName')
const editLastName = document.getElementById('editSurName')
const editAge = document.getElementById('editAge')
const editEmail = document.getElementById('editEmail')
const editPassword = document.getElementById('editPassword')
const editRoles = document.getElementById('editRoles')


function getAuthentication() {
    fetch(urlHead)
        .then(res => res.json())
        .then(user => {
            let role = user.username + ' with roles: '
            user.roles.forEach(r => {
                role+=r.roleName.replace('ROLE_', ' ')
            })
            header.innerHTML = role
        })
}
getAuthentication()



const fillingUserTable = () => {
    let res = ''
    fetch(url)
        .then(res => res.json())
        .then(users => {
            users.forEach(user => {
                res += `<tr>
                            <td>${user.id}</td>
                            <td>${user.userName}</td>
                            <td>${user.userLastName}</td>
                            <td>${user.userAge}</td>
                            <td>${user.userEmail}</td>
                            <td>${user.roles.map(r=>r.roleName.replace('ROLE_', ' '))}</td>
                            <td class="text-center"><button type="submit" class="btnEdit btn btn-primary" 
                                data-bs-toggle="modal" data-bs-target="#editModal">Edit</button></td>
                            <td class="text-center"><button type="submit" class="btnDel btn btn-danger" 
                                data-bs-toggle="modal" data-bs-target="#deleteModal">Delete</button></td>
                        </tr>`
            })
            tBody.innerHTML = res
        })

}

fetch(url)
    .then(response => response.json())
    .then(data => fillingUserTable(data))
    .catch(error => console.log(error))


function getAllRoles(target) {
    fetch(`${url}/allroles`)
        .then(res => res.json())
        .then(roles => {
            let roleList = ''
            roles.forEach(role => {
                roleList += `<option value='${role.id}'>${role.roleName.replace('ROLE_', ' ')}</option>`
            })
            target.innerHTML = roleList
        })
}

const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}


let headers = new Headers();
headers.append('Content-Type', 'application/json; charset=utf-8');

let roleList = (options) => {
    let array = []
    for (let i = 0; i < options.length; i++) {
        if (options[i].selected) {
            let role = {id: options[i].value}
            array.push(role)
        }
    }
    return array;
}

on(document, 'click', '.btnEdit', e => {

    e.preventDefault()
    let target = e.target.parentNode.parentNode

    editId.value = target.children[0].innerHTML
    editFirstName.value = target.children[1].innerHTML
    editLastName.value = target.children[2].innerHTML
    editAge.value = target.children[3].innerHTML
    editEmail.value = target.children[4].innerHTML
    editRoles.value = getAllRoles(editRoles)
})


on(document, 'click', '.btnDel', e => {
    e.preventDefault()
    let target = e.target.parentNode.parentNode
    id =target.children[0].innerHTML

    delId.value = target.children[0].innerHTML
    delFirstName.value = target.children[1].innerHTML
    delLastName.value = target.children[2].innerHTML
    delAge.value = target.children[3].innerHTML
    delEmail.value = target.children[4].innerHTML
    delRoles.value = getAllRoles(delRoles)
})


delModal.addEventListener('submit', (e) => {
    e.preventDefault()
    fetch(`${url}/${id}`, {
        method: 'DELETE',
    })
        .then(data => fillingUserTable(data))
        .catch(error => console.log(error))
    newDelModal.hide()
})


editModal.addEventListener('submit', (e) => {
    e.preventDefault()
    let options = document.querySelector('#editRoles');
    let setRoles = roleList(options)
    fetch(`${url}`, {
        method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({
            id: editId.value,
            userName: editFirstName.value,
            userLastName: editLastName.value,
            userAge: editAge.value,
            userEmail: editEmail.value,
            userPassword: editPassword.value,
            roles: setRoles
        })
    })
        .then(data => fillingUserTable(data))
        .catch(error => console.log(error))
    newEditModal.hide()
})

getAllRoles(roles)

newUser.addEventListener('submit', (e) => {
    e.preventDefault()
    let options = document.querySelector('#roles');
    let setRoles = roleList(options)
    fetch(`${url}`, {
        method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({
            userName: firstName.value,
            userLastName: lastName.value,
            userAge: age.value,
            userEmail: email.value,
            userPassword: password.value,
            roles: setRoles
        })
    })
        .then(data => fillingUserTable(data))
        .catch(error => console.log(error))
    alluserstab.show()
    firstName.value = ''
    lastName.value = ''
    age.value = ''
    email.value = ''
    password.value = ''
    roles.value = ''
})