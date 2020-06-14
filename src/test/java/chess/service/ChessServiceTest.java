package chess.service;

import chess.command.Command;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.dao.ChessDao;
import chess.dao.InMemoryChessDao;
import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ChessServiceTest {
    private ChessDao chessDAO;
    private ChessService chessService;
    private RequestDto moveRequest;
    private RequestDto saveRequest;
    private RequestDto endRequest;

    @BeforeEach
    void setUp() {
        chessDAO = new InMemoryChessDao();
        chessService = new ChessService(chessDAO);
        chessService.start();
        moveRequest = new RequestDto(Command.of("move"), Arrays.asList("a2", "a4"), 1L);
        saveRequest = new RequestDto(Command.of("end"), Arrays.asList("save", ""), 1L);
        endRequest = new RequestDto(Command.of("end"), Arrays.asList("", ""), 1L);
    }

    @Test
    void start() throws SQLException {
        ResponseDto responseDto = chessService.start();
        assertThat(chessDAO.findGameById(responseDto.getId())).isInstanceOf(ChessGame.class);
    }

    @Test
    void move() {
        ResponseDto responseDto = chessService.move(moveRequest);
        assertThat(responseDto.getBoard().get(Position.of("a4"))).isEqualTo("WHITEPAWN");
    }

    @Test
    void save() throws SQLException {
        chessService.end(saveRequest);
        assertThat(chessDAO.getDatabase().get(saveRequest.getId())).isInstanceOf(ChessGame.class);
    }

    @Test
    void end() throws SQLException {
        chessService.end(endRequest);
        assertThat(chessDAO.getDatabase().get(endRequest.getId())).isNull();
    }
}