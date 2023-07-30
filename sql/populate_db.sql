INSERT INTO worker (name, birthday, level, salary) VALUES
    (?, ?, ?, ?)
;

INSERT INTO client (name) VALUES
    ?, ?, ?, ?, ?
;

INSERT INTO project (client_id, start_date, finish_date) VALUES
    ((SELECT id FROM client LIMIT 1 OFFSET ?) ,? ,?)
;

INSERT INTO project_worker (project_id, worker_id)
    SELECT project.id, worker.id FROM project JOIN worker ON
    worker.id = (
        SELECT worker.id FROM worker
        WHERE worker.birthday < project.start_date
        ORDER BY worker.id LIMIT 1
    )
;