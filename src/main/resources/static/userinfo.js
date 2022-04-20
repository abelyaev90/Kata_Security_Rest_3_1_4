const url = '/api/user'
const urlHead = '/api/header'
const header = document.getElementById('head')
const tBody = document.querySelector('tbody')

function getAuthenticationForUserPage() {
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
getAuthenticationForUserPage()


let res = ''
const filling = (user) => {
    res += `<tr>
        <td>${user.id}</td>   
        <td>${user.username}</td>
        <td>${user.userLastName}</td>
        <td>${user.userAge}</td>
        <td>${user.userEmail}</td>
        <td>${user.roles.map(r=>r.roleName.replace('ROLE_', ' '))}</td>
        </tr>`
    tBody.innerHTML = res
}

fetch(url)
    .then(response => response.json())
    .then(data => filling(data))
    .catch(error => console.log(error))
