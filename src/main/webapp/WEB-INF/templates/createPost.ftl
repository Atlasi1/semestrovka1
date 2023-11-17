<#include "base.ftl"/>
<#import "_header.ftl" as header/>

<#macro title>
    Creating
</#macro>

<#macro content>
    <@header.header/>
    <div class="container mt-5">

        <h1>Создание поста</h1>

        <form method="post" enctype="multipart/form-data">

            <div class="form-group">
                <label>Название поста</label>
                <input type="text" class="form-control" name="title">
            </div>

            <div class="form-group">
                <label>Категории</label>
                <select class="form-control" id="postCategories" name="category">
                    <#list categories as cat>
                        <option value="${cat.getText()}">${cat.getText()}</option>
                    </#list>
                </select>
            </div>

            <div class="form-group">
                <label>Текст поста</label>
                <textarea class="form-control" name="content"></textarea>
            </div>

            <div class="form-group">
                <label>Добавить фото</label>
                <input type="file" class="form-control-file" name="photo">
            </div>

            <button type="submit" class="btn btn-primary">Опубликовать</button>

        </form>

    </div>
</#macro>

<@main/>