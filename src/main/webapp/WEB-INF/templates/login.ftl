<#include "base.ftl"/>

<#macro content>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <h2>Вход в аккаунт</h2>
                <form id="loginForm" method="post">
                    <div class="form-group">
                        <label for="email">Электронная почта:</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Пароль:</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe">
                        <label class="form-check-label" for="rememberMe">Запомнить меня</label>
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">Войти</button>
                </form>
                <a href="/register">Зарегистрироваться</a>
            </div>
        </div>
    </div>
</#macro>

<@main />