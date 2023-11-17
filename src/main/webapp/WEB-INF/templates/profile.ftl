<#include "base.ftl"/>
<#import "_header.ftl" as header/>

<#macro title>
    Профиль
</#macro>

<#macro content>
    <@header.header/>
    <div class="container">
        <div class="row">
            <div class="col-md-3 border-end border-dark">
                <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="${user.getAvatar!}"><span class="font-weight-bold">${user.getUsername()}</span><span class="text-black-50">${user.getEmail()}</span><span> </span></div>
            </div>
            <div class="col">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="text-right">Изменить данные профиля</h4>
                    </div>
                    <form id="dataForm" enctype="multipart/form-data">
                        <div class="row mt-2">
                            <div class="col-md-6"><label class="labels">Юзернейм</label><input type="text" name="name" class="form-control" placeholder="Напишите имя" value="${user.getUsername()}"></div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-6"><label class="labels">Email</label><input type="email" name="email" class="form-control" placeholder="Напишите почту" value="${user.getEmail()}"></div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-6"><label class="labels">Password</label><input type="password" name="password" class="form-control" placeholder="Напишите пароль"></div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-6"><label class="labels">Аватар</label><input type="file" accept="image/*" name="photo" class="form-control" value="" placeholder="state"></div>
                        </div>
                        <div class="mt-5"><input type="submit" id="reform" class="btn btn-primary profile-button" value="Сохранить изменения"></div>
                    </form>
                    <div id="response"></div>
                </div>
            </div>
        </div>
<#--        <div class="row">-->
<#--            Список постов юзера-->
<#--        </div>-->
        <script type="application/javascript">
            $(document).ready(function() {
                $("#dataForm").submit(function(e) {
                    e.preventDefault();

                    var formData = new FormData(e.target);

                    $.ajax({
                        url: "/changeprofile",
                        type: "POST",
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function() {
                            $("#response").text("Данные успешно изменены");
                        },
                        error: function() {
                            $("#response").text("Произошла ошибка при изменении данных");
                        }
                    });
                });
            });
        </script>
    </div>
</#macro>

<@main/>