<#include "base.ftl"/>

<#macro content>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h2>Регистрация пользователя</h2>
                <form method="post" enctype="multipart/form-data" id="registrationForm">
                    <div class="form-group">
                        <label for="username">Имя пользователя (от 4 до 70):</label>
                        <input type="text" class="form-control" id="username" name="username" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Пароль (от 8 до 64):</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword">Подтверждение пароля:</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Почта:</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="photo">Фотография:</label>
                        <input type="file" accept="image/*" class="form-control" id="photo" name="photo">
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">Зарегистрироваться</button>
                </form>
                <div class="alert alert-danger mt-3" id="passwordError" style="display: none;">
                    Пароль и подтверждение пароля не совпадают.
                </div>
                <div class="alert alert-danger mt-3" id="emailError" style="display: none;">
                    Неправильное почта.
                </div>
                <div class="alert alert-danger mt-3" id="usernameError" style="display: none;">
                    Неправильное имя пользователя.
                </div>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function() {
            $("#registrationForm").submit(function(event) {
                var password = $("#password").val();
                var confirmPassword = $("#confirmPassword").val();
                var email = $('#email').val();
                var username = $('#username').val()
                if (password !== confirmPassword) {
                    $("#passwordError").show();
                    event.preventDefault();
                }
                if(!(4 <= username <= 70) && !username.match("^[a-zA-Z0-9а-яА-Я_\\-]+$")) {
                    $("#usernameError").show();
                    event.preventDefault();
                }
                if(!(email <= 255) && !email.match("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$") {
                    $("#emailError").show();
                    event.preventDefault();
                }
                }
                );
        });
    </script>
</#macro>

<@main />