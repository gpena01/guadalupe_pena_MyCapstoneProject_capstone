package kanban_board.controller;

import kanban_board.models.Board;
import kanban_board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller class handles incoming http requests
@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        Board createdBoard = boardService.saveBoard(board);
        return new ResponseEntity<>(createdBoard, HttpStatus.CREATED);
    }
    @GetMapping("/{boardId}")
    public ResponseEntity<Board> getBoardById(@PathVariable long boardId) {
        Board board = boardService.getBoardById(boardId);
        return ResponseEntity.ok(board);
    }
    @GetMapping
    public ResponseEntity<List<Board>> getAllBoards() {
        List<Board> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }
    @PutMapping("/{boardId}")
    public ResponseEntity<Board> updateBoard(@PathVariable long boardId, @RequestBody Board updatedBoard) {
        Board board = boardService.getBoardById(boardId);
        board.setBoardTitle(updatedBoard.getBoardTitle());

        Board updatedBoard1 = boardService.updateBoard(board);
        return ResponseEntity.ok(updatedBoard1);
    }
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.noContent().build();
    }
}
