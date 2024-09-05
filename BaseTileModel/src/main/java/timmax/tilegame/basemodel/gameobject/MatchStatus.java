package timmax.tilegame.basemodel.gameobject;

//  Статус матча
//  Этот статус определяет возможность делания игрового хода:
//  - нельзя, т.к. статус не определён (MatchStatus0Undefined) - используется для инициализации расстановки.
//  - можно, т.к. матч "в игре" (MatchStatus1Running).
//  - нельзя, т.к. матч "окончен" (MatchStatus2GameOver).
public sealed abstract class MatchStatus
        permits MatchStatus0Undefined, MatchStatus1Running, MatchStatus2GameOver {
}
