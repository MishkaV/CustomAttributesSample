# CustomAttributesSample
![telegram-cloud-document-2-5282757925234808592](https://user-images.githubusercontent.com/54765046/157656680-101aa890-0c08-4a21-b878-c49f391b0e1a.gif)

## Семпл кастомной View со своими атрибутами, которые описаны в `res/values/attr.xml`:
```
<declare-styleable name="PacManView">
        <attr name="pacmanColor" format="color"/>
        <attr name="ghostColor" format="color"/>
        <attr name="isEating" format="boolean"/>
</declare-styleable>
```
## Описание атрибутов
- pacmanColor - цвет Pacman'а
- ghostColor - цвет призрака
- isEating - положение Pacman'а - кушает или в предвкушении...
