UPDATE specialite SET
                      code = ?,
                      nom = ?,
                      updatedAt = NOW()
WHERE id = ?;