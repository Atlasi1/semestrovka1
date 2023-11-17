<#macro has_user>
    <#if user??>
        <div class="profile">
            <div class="profile-info" class="nav-item" onclick="location.href='/profile';" style="cursor: pointer;">
                <#if user.getPhoto()??>
                    <img src="${user.getPhoto()}" alt="alt"/>
                <#else>
                    <img src="/icons/user.png"/>
                </#if>
                <span>${user.getUsername()}</span>
                <a href="/logout" class="nav-link" style="color: white">Выйти из аккаунта</a>
            </div>
        </div>
    <#else>
        <div class="profile-info nav-item" style="padding-right: 10px">
            <a href="/login" class="nav-link" style="color: white">Войти в аккаунт</a>
            <div class="vr"></div>
            <span><a href="/register" class="nav-link" style="color: white">Зарегистрироваться</a></span>
        </div>
    </#if>
</#macro>